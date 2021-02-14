<template>
  <div>
    <div v-if="!profile">Необходимо авторизоваться через
      <a href="/login">Google</a>
    </div>
    <div v-else>
      <div>{{ profile.name }}&nbsp;<a href="/logout">Выйти</a></div>
      <messages-list :messages="messages" :profile="profile" v-on:update:profile="profile = $event"/>
    </div>
  </div>
</template>

<script>
import MessagesList from '../components/messages/MessageList.vue'
import {addHandler} from '../util/ws'
import {getIndex} from '../util/collections'

export default {
  components: {
    MessagesList
  },
  data() {
    return {
      messages: frontendData.messages,
      profile: frontendData.profile
    }
  },
  created() {
    addHandler(data => {
      if (!data.messageId) {
        if (this.profile.id === data.user.id || this.profile.subscriptions.some((user) => user.id === data.user.id)) {
          let index = getIndex(this.messages, data.id)
          if (index > -1) {
            this.messages.splice(index, 1, data)
          } else {
            this.messages.push(data)
          }
        }
      } else {
        let messageId = this.messages.findIndex((message) => message.id === data.messageId)
        let message = this.messages[messageId];

        if (message.comments) {

          message.comments.push(data);
        } else {
          message.comments = [data];
        }
      }
    })
  }
}
</script>

<style>

</style>
