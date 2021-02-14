<template>
  <div style="margin-top: 15px">
    <i>(MsgId: №{{ message.id }})</i>
    text: {{ message.text }}
    Author: {{ message.user.name }}
    <span style="float: right; margin-right: 20px">
            <input type="button" value="Edit" @click="edit"/>
            <input type="button" value="X" @click="del"/>
        </span>

    <div>
      <input type="text" placeholder="Add text comment" v-model="commentText"/>
      <input type="button" value="Save comment" @click="save()"/>
    </div>

    <li v-for="comment in message.comments" :key="comment.id">
      {{ comment.text }}
      {{ comment.author.name }}
    </li>

  </div>
</template>

<script>
import {sendComment} from '../../util/ws'

export default {
  props: ['message', 'editMessage', 'deleteMessage', 'messages'],
  data() {
    return {
      commentText: ''
    }
  },
  methods: {
    save() {
      sendComment({
        id: null, message: {...this.message}, messageId: this.message.id, text: this.commentText
      })
      this.commentText = ''
    },
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.deleteMessage(this.message)
    }
  }
}
</script>

<style>

</style>
