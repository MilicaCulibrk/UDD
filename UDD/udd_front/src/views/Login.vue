<template>
  <v-row>
    <v-col align="center" justify="center">
      <v-container>
        <v-snackbar
          v-model="alert.show"
          :timeout="alert.timeout"
          :color="alert.color"
          top
          right
          style="margin-top: 2%"
          >{{ alert.text }}</v-snackbar
        >

        <v-form v-model="form.valid">
          <v-card style="width: 40%; margin-top: 10%">
            <div class="detailsBorderColor elevation-24">
              <v-card-title justify="center">
                <v-col>
                  <h4 class="primary--text headline">Login</h4>
                </v-col>
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      v-model="user.username"
                      :rules="form.rules.username"
                      label="Username"
                      type="text"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      v-model="user.password"
                      :rules="form.rules.password"
                      label="Password"
                      type="password"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col rows="10"></v-col>
                  <v-col>
                    <v-btn color="primary white--text" @click="login"
                      >Login</v-btn
                    >
                  </v-col>
                </v-row>
              </v-card-text>
            </div>
          </v-card>
        </v-form>
      </v-container>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapMutations } from "vuex";
export default {
  data: () => ({
    user: {
      username: null,
      password: null,
    },
    form: {
      valid: false,
      rules: {
        username: [(v) => !!v || "Username is required"],
        password: [(v) => !!v || "Password is required"],
      },
    },
    alert: {
      show: false,
      text: "Error",
      timeout: 2000,
      color: "red",
    },
  }),
  methods: {
    ...mapMutations(["setUser"]),
    login() {
      if (this.form.valid) {
        axios
          .post("/login", this.user)
          .then((response) => {
            this.setUser(response.data);
            this.$router.push("/");
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
  border-left: 1.5px solid #fbc02d;
  border-top: 1.5px solid #fbc02d;
  border-right: 1.5px solid #fbc02d;
  border-bottom: 1.5px solid #fbc02d;
}
</style>