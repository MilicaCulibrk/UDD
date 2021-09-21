package com.milicaculibrk.udd.service.book;

import com.milicaculibrk.udd.model.IndexUnit;
import com.milicaculibrk.udd.dto.UploadBookDTO;
import com.milicaculibrk.udd.model.Writer;
import com.milicaculibrk.udd.repository.WriterRepository;
import com.milicaculibrk.udd.service.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class BookService {

    private static String DATA_DIR_PATH;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("application");
        DATA_DIR_PATH = rb.getString("dataDir");
    }

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private Indexer indexer;

    public void addNewBook(String title, String writer, String[] genres, String keywords, MultipartFile files) throws IOException{

        try {
            indexBook(generateNewModel(title, writer, genres, keywords, files));
        } catch (IOException e) {

        }
    }

    private UploadBookDTO generateNewModel(String title, String writer, String[] genres, String keywords, MultipartFile files){

        UploadBookDTO uploadBookDTO = new UploadBookDTO();

        uploadBookDTO.setTitle(title);
        uploadBookDTO.setGenres(genres);
        uploadBookDTO.setKeywords(keywords);

        List<MultipartFile> filesList = new ArrayList();
        filesList.add(files);
        uploadBookDTO.setFiles(filesList);

        Writer bookWriter = writerRepository.findById(writer).get();
        uploadBookDTO.setWriter(bookWriter.getFirstName() + " " + bookWriter.getLastName());

        return uploadBookDTO;
    }

    //dto se prevodi u index unit jer se on mora tako cuvati i onda se samo pozove indexer, isto njihova fukncija valjda
    //NIJE - Indexer
    private void indexBook(UploadBookDTO uploadBookDto) throws IOException{

        for (MultipartFile file : uploadBookDto.getFiles()) {

            if (file.isEmpty())
                continue;

            String pathName = saveBook(file);

            if(pathName != null){

                IndexUnit indexUnit = indexer.getPDFHandler(pathName).getIndexUnit(new File(pathName));
                //dodas u elastic fajl, tj ceo objekat koji u sebi ima file, to je index unit to moras imati
                indexer.add(setIndexUnitFields(indexUnit, uploadBookDto));
            }
        }
    }

    private IndexUnit setIndexUnitFields(IndexUnit indexUnit, UploadBookDTO uploadBookDto){

        indexUnit.setTitle(uploadBookDto.getTitle());
        indexUnit.setWriter(uploadBookDto.getWriter());
        indexUnit.setKeywords(uploadBookDto.getKeywords());

        String genres = "";
        for (String genre: uploadBookDto.getGenres()) {
            genres += genre + " ";
        }
        indexUnit.setGenres(genres);

        return indexUnit;
    }

    private String saveBook(MultipartFile file) throws IOException {

        String retVal = null;

        if (file.isEmpty())
            return retVal;

        byte[] bytes = file.getBytes();
        Path path = Paths.get(getResourceFilePath(DATA_DIR_PATH).getAbsolutePath() + File.separator + file.getOriginalFilename());
        Files.write(path, bytes);
        retVal = path.toString();
        return retVal;
    }

    private File getResourceFilePath(String path) {

        URL url = this.getClass().getClassLoader().getResource(path);
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        return file;
    }
}
