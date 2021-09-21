<template>
  <v-container fluid>
    <!-- snackbar -->
    <v-snackbar v-model="snackbarSuccess" :timeout="4000" top color="success">
      <span>{{ snackbarSuccessText }}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>

    <v-snackbar v-model="snackbarDanger" :timeout="4000" top color="danger">
      <span>{{ snackbarDangerText }}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>

    <v-row class="mt-4 ml-4 mr-2">
      <v-col>
        <div class="detailsBorderColor rounded">
          <v-card elevation="24">
            <!-- <v-card-title justify="center">
                <h5>Search</h5>
              </v-card-title> -->
            <v-card-text>
              <v-row class="ml-1 mb-n6">
                <v-col cols="8">
                  <v-text-field
                    v-model="search.title"
                    label="Title"
                    type="text"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-radio-group v-model="search.operatorTitle" row>
                    <v-radio
                      v-for="op in operator"
                      :key="op"
                      :label="op"
                      :value="op"
                    ></v-radio>
                  </v-radio-group>
                </v-col>
              </v-row>
              <v-row class="ml-1 mb-n6">
                <v-col cols="8">
                  <v-text-field
                    v-model="search.writer"
                    label="Writer"
                    type="text"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-radio-group v-model="search.operatorWriter" row>
                    <v-radio
                      v-for="op in operator"
                      :key="op"
                      :label="op"
                      :value="op"
                    ></v-radio>
                  </v-radio-group>
                </v-col>
              </v-row>
              <v-row class="ml-1 mb-n6">
                <v-col cols="8">
                  <v-text-field
                    v-model="search.genres"
                    label="Genre"
                    type="text"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-radio-group v-model="search.operatorGenres" row>
                    <v-radio
                      v-for="op in operator"
                      :key="op"
                      :label="op"
                      :value="op"
                    ></v-radio>
                  </v-radio-group>
                </v-col>
              </v-row>
              <v-row class="ml-1 mb-n6">
                <v-col cols="8">
                  <v-text-field
                    v-model="search.keyWords"
                    label="Key Words"
                    type="text"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-radio-group v-model="search.operatorKeyWords" row>
                    <v-radio
                      v-for="op in operator"
                      :key="op"
                      :label="op"
                      :value="op"
                    ></v-radio>
                  </v-radio-group>
                </v-col>
              </v-row>
              <v-row align="center" justify="center" class="ml-1 mb-n6">
                <v-col cols="8">
                  <v-textarea
                    v-model="search.text"
                    label="Text"
                    outlined
                    dense
                  ></v-textarea>
                </v-col>
                <v-col cols="4">
                  <v-radio-group v-model="search.operatorText" row>
                    <v-radio
                      v-for="op in operator"
                      :key="op"
                      :label="op"
                      :value="op"
                    ></v-radio>
                  </v-radio-group>
                </v-col>
              </v-row>
              <v-row align="center" justify="center" class="mt-n8 ml-5">
                <v-col cols="9">
                  <v-row>
                    <v-radio-group v-model="search.searchType" row>
                      <v-radio
                        v-for="st in searchType"
                        :key="st"
                        :label="st"
                        :value="st"
                      ></v-radio>
                    </v-radio-group>
                    <h10 class="ml-2 mt-5 primary--text">(Type of search)</h10>
                  </v-row>
                </v-col>
                <v-col>
                  <v-btn medium color="primary white--text" @click="searchBook"
                    >Search
                  </v-btn>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </div>
        <v-tooltip bottom color="black">
          <template v-slot:activator="{ on }">
            <v-btn
              @click="clearFields"
              icon
              v-on="on"
              class="ml-12 mt-n6 red lighten-2"
            >
              <v-icon large color="white">mdi-close</v-icon>
            </v-btn>
          </template>
          <span class="primary--text">Clear fields</span>
        </v-tooltip>
      </v-col>
      <v-col cols="8">
        <v-row>
          <v-container>
            <v-data-table
              :headers="headers"
              :items="items"
              hide-default-footer
              class="elevation-24 secondary lighten-1"
              style="overflow: auto"
            >
              <template v-slot:item.actions="{ item }">
                <v-btn icon @click="downloadBook(item)">
                  <v-icon color="primary">mdi-download</v-icon>
                </v-btn>
              </template>
            </v-data-table>
          </v-container>
        </v-row>
        <v-row>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <LoginComponent
            class="mr-n12 ml-12"
            @addedBook="
              snackbarSuccess = true;
              snackbarSuccessText = 'You successfully added a book!';
              searchBook();
            "
          />
          <v-tooltip bottom color="black">
            <template v-slot:activator="{ on }">
              <v-btn
                @click="clearResults"
                v-on="on"
                icon
                class="mr-8 mt-n6 red lighten-2"
              >
                <v-icon large color="white">mdi-close</v-icon>
              </v-btn>
            </template>
            <span class="primary--text">Clear results</span>
          </v-tooltip>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import LoginComponent from "./Add.vue";
export default {
  components: {
    LoginComponent,
  },
  name: "Home",
  data() {
    return {
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      headers: [
        {
          text: "Title",
          sortable: true,
          value: "title",
        },
        {
          text: "Writer",
          sortable: true,
          value: "writer",
        },
        {
          text: "Genres",
          sortable: false,
          value: "genres",
        },
        {
          text: "Key Words",
          sortable: false,
          value: "keywords",
        },
        { text: "Download", value: "actions", sortable: false },
      ],
      items: [],
      operator: ["AND", "OR"],
      searchType: ["regular", "phrase"],
      search: {
        title: "",
        operatorTitle: "AND",
        writer: "",
        operatorWriter: "AND",
        genres: "",
        operatorGenres: "AND",
        keyWords: "",
        operatorKeyWords: "AND",
        text: "",
        operatorText: "AND",
        searchType: "regular",
      },
    };
  },
  methods: {
    searchBook() {
      axios
        .post("/search", this.search)
        .then((response) => {
          this.items = response.data.content;
          console.log(response.data);
        })
        .catch((error) => {});
    },
    clearFields() {
      this.search.title = "";
      this.search.genres = "";
      this.search.writer = "";
      this.search.keyWords = "";
      this.search.text = "";
      this.search.operatorTitle = "AND";
      this.search.operatorWriter = "AND";
      this.search.operatorGenres = "AND";
      this.search.operatorKeyWords = "AND";
      this.search.operatorText = "AND";
      this.search.searchType = "regular";
    },
    clearResults() {
      axios
        .get("/index")
        .then((response) => {
          this.items = response.data.content;
        })
        .catch((error) => {});
    },
    downloadBook(item) {
      let url = item.filename;
      let formData = new FormData();
      formData.append("url", url);
      axios
        .post("/index/download", formData)
        .then((response) => {
          var fileURL = window.URL.createObjectURL(new Blob([response.data]));
          var fileLink = document.createElement("a");

          fileLink.href = fileURL;
          fileLink.setAttribute("download", item.title + ".pdf");
          document.body.appendChild(fileLink);

          fileLink.click();
          // let blob = new Blob([response.data], { type: "application/pdf" });
          // let link = document.createElement("a");
          // link.style.display = "none";
          // link.href = window.URL.createObjectURL(blob);
          // link.download = item.title + ".pdf";
          // document.body.appendChild(link);
          // link.click();
          // console.log(response.data);
        })
        .catch((error) => {});
    },
  },
  mounted() {
    this.searchBook();
  },
};
</script>

<style scoped>
.detailsBorderColor {
  border-left: 1.5px solid #fbc02d;
  border-top: 1.5px solid #fbc02d;
  border-right: 1.5px solid #fbc02d;
  border-bottom: 1.5px solid #fbc02d;
}
</style>
