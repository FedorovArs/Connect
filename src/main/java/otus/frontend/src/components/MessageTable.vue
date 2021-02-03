<template>
  <div>
    <v-data-table
        :headers="headers"
        :items="getMessages"
        :footer-props="{'items-per-page-options': [25, 100, 500, -1]}"
    >

      <template v-slot:item="{ item }">
        <tr>
          <td>
            <v-layout justify-start>
              {{ item.text }}
            </v-layout>
          </td>

          <td>
            <v-layout justify-start>
              <v-icon
                  small
                  class="mr-2"
                  @click="editItem(item)"
              >
                mdi-pencil
              </v-icon>
              <v-icon
                  small
                  @click="deleteItem(item)">
                mdi-delete
              </v-icon>
            </v-layout>
          </td>
        </tr>
      </template>

    </v-data-table>

    <EditMessageDialog v-if="this.$store.getters.getIsEditDialogOpen"></EditMessageDialog>
    <DeleteMessageDialog v-if="this.$store.getters.getIsDeleteDialogOpen"></DeleteMessageDialog>
  </div>

</template>

<script>
import EditMessageDialog from "../components/EditMessageDialog";
import DeleteMessageDialog from "../components/DeleteMessageDialog";
import * as utils from "../js/utils";

let messageUrl = utils.getHost() + utils.MESSAGE_URL;
let headersUrl = utils.getHost() + utils.HEADERS_URL;

export default {
  name: "MessageTable",
  data: () => ({
    headers: [],
  }),
  methods: {
    editItem(item) {
      this.$store.commit('setEditedItem', item);
      this.$store.commit('setIsEditDialogOpen', true)
    },

    deleteItem(item) {
      this.$store.commit('setEditedItem', item);
      this.$store.commit('setIsDeleteDialogOpen', true)
    }
  },
  computed: {
    getMessages() {
      return this.$store.getters.getMessages;
    }
  },
  created() {
    this.$http.get(headersUrl).then(response => {
      this.headers = response.data;
    });

    this.$http.get(messageUrl).then(response => {
      this.$store.commit('saveOrUpdateMessages', response.data);
    });
  },
  components: {
    EditMessageDialog,
    DeleteMessageDialog
  },
}
</script>

<style scoped>

</style>