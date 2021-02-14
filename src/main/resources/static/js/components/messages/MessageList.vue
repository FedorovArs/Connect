<template>
  <div style="overflow: hidden;">
    <div style="float: left; width: 600px; ">
      <message-form :messages="messages" :messageAttr="message"/>
      <message-row v-for="message in messages"
                   :key="message.id"
                   :message="message"
                   :editMessage="editMessage"
                   :deleteMessage="deleteMessage"
                   :messages="messages"/>
    </div>

    <div style="float: left; width: 500px; border:1px solid black ">

      <li v-for="user in users" :key="user.id">
        {{ user.name }}
        <input style="float: right; margin-right: 20px" type="button"
               v-bind:value="profile.subscriptions.some((sub) => sub.id === user.id) ? 'Unsubscribe' : 'Subscribe'"
               @click="subscribe(user.id)"/>
      </li>
    </div>
  </div>
</template>

<script>
import MessageRow from 'components/messages/MessageRow.vue'
import MessageForm from 'components/messages/MessageForm.vue'

export default {
  props: ['messages', 'profile'],
  components: {
    MessageRow,
    MessageForm
  },
  data() {
    return {
      message: null,
      users: []
    }
  },
  methods: {
    subscribe(userId) {
      this.$resource('/profile/' + userId).save({user: {...this.profile}}).then(response => {
        this.users = response.data.filter((user) => user.id !== this.profile.id);
      })

    },
    editMessage(message) {
      this.message = message
    },
    deleteMessage(message) {
      this.$resource('/message{/id}').remove({id: message.id}).then(result => {
        if (result.ok) {
          this.messages.splice(this.messages.indexOf(message), 1)
        }
      })
    },
    getAuthorizedUsers() {
      this.$resource('/user/all').get().then(response => {
        this.users = response.data.filter((user) => user.id !== this.profile.id);
      })
    }
  },
  mounted() {
    this.getAuthorizedUsers()
    this.timerId = setInterval(() => this.getAuthorizedUsers(), 20000);
  },
}
</script>

<style>
</style>
