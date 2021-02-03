<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="430px">
      <v-card>
        <v-card-title class="title">Delete a message?
        </v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green" small outlined @click="$store.commit('setIsDeleteDialogOpen', false)">Cancel
          </v-btn>
          <v-btn color="red" small outlined @click="deleteLocationType">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import * as utils from "../js/utils";

let messageUrl = utils.getHost() + utils.MESSAGE_URL;
export default {

  name: "DeleteMessageDialog",
  data: () => ({
    dialog: true,
  }),
  methods: {
    deleteLocationType() {
      this.$http.delete(messageUrl + '/' + this.$store.getters.getEditedItem.id)
          .then(() => {
                this.$store.commit('deleteMessageById', this.$store.getters.getEditedItem.id);
                this.$store.commit('setEditedItem', null);
              }
          )
      ;

      this.$store.commit('setIsDeleteDialogOpen', false);
    }
  }
}
</script>

<style scoped>

</style>