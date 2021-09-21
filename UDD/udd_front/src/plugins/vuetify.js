import Vue from "vue";
import Vuetify from "vuetify/lib";

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#FBC02D", //zuta
                secondary: "#FFF9C4", //svetlo zuta
                success: "#A5D6A7", //svetlo zelena
                danger: "#EF9A9A", //svetlo crvena
            },
        },
    },
});