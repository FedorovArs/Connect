<template>
  <v-app>
    <v-main>
      <v-container :fluid="false">

        <AddMessage></AddMessage>
        <MessageTable></MessageTable>

      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AddMessage from "../components/AddMessage";
import MessageTable from "../components/MessageTable";

export default {
  name: 'Message',
  components: {
    AddMessage,
    MessageTable
  },
  data: () => ({}),
  methods: {},
  computed: {},
  created() {
  },
}
</script>
