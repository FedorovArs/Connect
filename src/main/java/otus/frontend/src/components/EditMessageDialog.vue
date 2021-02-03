<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="450px">
        <v-card>
          <v-card-title>
            <span class="headline">Edit message</span>
          </v-card-title>

          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field v-model="message"
                                :clearable="true"></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="red" small outlined @click="$store.commit('setIsEditDialogOpen', false)">Cancel
            </v-btn>
            <v-btn color="green" small outlined @click="editLocationType">Edit</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import * as utils from "../js/utils.js";

let messageUrl = utils.getHost() + utils.MESSAGE_URL;

export default {
  name: "EditLocationTypeDialog",
  data: () => ({
    dialog: true,
    message: "",
  }),
  methods: {
    editLocationType() {
      this.$http.put(messageUrl + '/' + this.$store.getters.getEditedItem.id,
          {
            text: this.message
          }
      )
          .then(response => {
            this.$store.commit('saveUpdatedMessage', response.data)
            this.$store.commit('setEditedItem', null);
            this.$store.commit('setIsEditDialogOpen', false);
          })
    }
  },
  created() {
    this.message = this.$store.getters.getEditedItem.text;
  }
}
</script>

<style scoped>

</style>