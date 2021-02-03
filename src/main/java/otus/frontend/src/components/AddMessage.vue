<template>
  <div>
    <v-row
        align="center"
        justify="space-around"
    >
      <v-col cols="10">
        <v-text-field
            v-model="newMessage"
            label="Input text"></v-text-field>
      </v-col>

      <v-col cols="2">
        <v-spacer></v-spacer>
        <v-btn
            tile
            color="success"
            @click="addMessage"
        >
          <v-icon left>
            mdi-comment-plus-outline
          </v-icon>
          Add Message
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import * as utils from "../js/utils";

let messageUrl = utils.getHost() + utils.MESSAGE_URL;

export default {
  name: "AddMessage",
  data: () => ({
    newMessage: "",
  }),
  methods: {
    addMessage() {
      if (this.newMessage.length > 0) {
        this.$http.post(messageUrl, {text: this.newMessage}).then(response => {
          this.$store.commit('addMessage', response.data);
          this.newMessage = '';
        });

      } else {
        alert("Please input message text");
      }
    },
  },
}

</script>

<style scoped>

</style>