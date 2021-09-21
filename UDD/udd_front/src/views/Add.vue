<template  >
  <v-row justify="center">
    <v-dialog v-model="LoginDialog" max-width="600px">
      <template v-slot:activator="{ on }">
        <v-tooltip bottom color="black">
          <template #activator="{ on: tooltip }">
            <v-btn
              icon
              class="mt-n6 green lighten-1"
              v-on="{ ...tooltip, ...on }"
            >
              <v-icon large color="white">mdi-plus</v-icon>
            </v-btn>
          </template>
          <span class="primary--text">Add Book</span>
        </v-tooltip>
      </template>

      <v-form
        v-model="form.valid"
        method="POST"
        enctype="multipart/form-data"
        id="fileUploadForm"
        ref="form"
      >
        <div class="detailsBorderColor">
          <v-card>
            <v-card-title justify="center">
              <v-col>
                <h4
                  class="primary--text font-italic"
                  justify="center"
                  align="center"
                >
                  Add new book
                </h4>
              </v-col>
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="book.title"
                    :rules="form.rules.title"
                    label="Title"
                    type="text"
                    name="title"
                  ></v-text-field>
                </v-col>

                <v-col cols="12">
                  <v-select
                    :items="genres"
                    label="Genre"
                    multiple
                    outlined
                    v-model="book.genres"
                    :rules="form.rules.genres"
                    name="genres"
                  ></v-select>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    v-model="book.keywords"
                    :rules="form.rules.keywords"
                    label="Key Words"
                    type="text"
                    name="keywords"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-file-input
                    v-model="book.files"
                    :rules="form.rules.files"
                    label="Document"
                    name="files"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="10"></v-col>
                <v-col>
                  <v-btn
                    justify="center"
                    align="center"
                    type="submit"
                    color="primary white--text"
                    @click.prevent="addBook"
                    @click="LoginDialog = false"
                    >Add</v-btn
                  >
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </div>
      </v-form>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
export default {
  data: () => ({
    book: {
      title: null,
      keywords: null,
      genres: null,
      files: null,
    },
    form: {
      valid: false,
      rules: {
        title: [(v) => !!v || "Title is required"],
        keywords: [(v) => !!v || "Key words is required"],
        genres: [(v) => !!v || "Genre is required"],
        files: [(v) => !!v || "File is required"],
      },
    },
    alert: {
      show: false,
      text: "Error",
      timeout: 2000,
      color: "red",
    },
    genres: [
      "Romance",
      "Self Help",
      "Psychology",
      "Science Fiction",
      "Action",
      "Thriller",
      "Horror",
      "Comedy",
      "Classic",
      "Historic",
      "Mistery",
    ],
  }),
  computed: {
    ...mapGetters(["user"]),
  },
  methods: {
    addBook() {
      if (this.form.valid) {
        let formData = new FormData();
        Object.keys(this.book).forEach((key) => {
          formData.append(key, this.book[key]);
        });
        formData.append("writer", this.user.username);
        axios
          .post("/index", formData)
          .then((response) => {
            this.$refs.form.reset();
            this.alert.color = "green";
            this.alert.text = response.data;
            this.alert.show = true;
            this.$emit("addedBook");
          })
          .catch((error) => {
            this.alert.text = error.response.data;
            this.alert.color = "red";
            this.alert.show = true;
          });
      } else {
        this.alert.color = "red";
        this.alert.text = "All fields are required.";
        this.alert.show = true;
      }
    },
  },
};
</script>

<style scoped>
.detailsBorderColor {
  border-left: 2px solid #fbc02d;
  border-top: 2px solid #fbc02d;
  border-right: 2px solid #fbc02d;
  border-bottom: 2px solid #fbc02d;
}
</style>