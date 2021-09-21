package com.milicaculibrk.udd.service;

import com.milicaculibrk.udd.handler.PDFHandler;
import com.milicaculibrk.udd.model.IndexUnit;
import com.milicaculibrk.udd.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Indexer {
	
	@Autowired
	private BookRepository bookRepository;

	public Indexer() {
	}
	
	
	public boolean delete(String filename){

		bookRepository.delete(bookRepository.findById(filename).get());
		return true;
		
	}
	
	public boolean update(IndexUnit unit){
		unit = bookRepository.save(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}

	public boolean add(IndexUnit indexUnit){

		indexUnit = bookRepository.index(indexUnit);

		if(indexUnit == null)
			return false;

		return true;
	}
	
	/**
	 * 
	 * @param file Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
	 */
	public int index(File file){		
		PDFHandler handler = null;
		String fileName = null;
		int retVal = 0;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					handler = getPDFHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}	
					if(add(handler.getIndexUnit(newFile)))
						retVal++;
				} else if (newFile.isDirectory()){
					retVal += index(newFile);
				}
			}
			System.out.println("indexing done");
		} catch (Exception e) {
			System.out.println("indexing NOT done");
		}
		return retVal;
	}
	

	public PDFHandler getPDFHandler(String pathName){

		if(!pathName.endsWith(".pdf"))
			return null;

		return new PDFHandler();
	}
}