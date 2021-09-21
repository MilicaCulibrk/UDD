<template>
  <v-app>
    <div>
      <v-app-bar
        src="https://picsum.photos/id/1073/2000/3000"
        scroll-target="#scrolling-techniques-3"
      >
        <router-link to="/" tag="h3" class="white rounded primary--text"
          >Literary association
          <v-icon class="primary--text">mdi-book</v-icon>
        </router-link>

        <v-spacer></v-spacer>

        <v-btn
          text
          class="white primary--text mr-2"
          @click="logout"
          v-if="user != null"
        >
          Logout
          <v-icon color="primary">mdi-logout</v-icon>
        </v-btn>

        <v-btn
          to="/login"
          text
          class="white primary--text mr-2"
          v-if="user == null"
        >
          Login
          <v-icon>mdi-login</v-icon>
        </v-btn>
      </v-app-bar>

      <v-main>
        <router-view />
      </v-main>
    </div>
  </v-app>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "App",
  data: () => ({}),
  computed: {
    ...mapGetters(["user"]),
  },
  methods: {
    ...mapMutations(["setUser"]),
    logout() {
      this.setUser(null);
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
a:hover {
  text-decoration: none;
}

h3:hover {
  cursor: pointer;
}
</style>
