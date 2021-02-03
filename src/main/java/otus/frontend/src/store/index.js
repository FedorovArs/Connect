import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    messages: [],
    editedItem: null,
    isEditDialogOpen: false,
    isDeleteDialogOpen: false,
  },
  mutations: {
    saveOrUpdateMessages(state, messages) {
        state.messages = messages;
    },
    saveUpdatedMessage(state, message) {
        let editedMessage = state.messages.filter((s) => s.id === message.id);
        editedMessage[0].text = message.text;
    },
    addMessage(state, message) {
        let messages = state.messages;
        messages.push(message);
    },
    setIsEditDialogOpen(state, isOpen) {
       state.isEditDialogOpen = isOpen;
    },
    setIsDeleteDialogOpen(state, isOpen) {
       state.isDeleteDialogOpen = isOpen;
    },
    setEditedItem(state, item) {
        state.editedItem = item;
    },
    deleteMessageById(state, id) {
        state.messages = state.messages.filter(item => item.id !== id);
    },
  },
  getters: {
    getMessages: (state) => state.messages,
    getIsEditDialogOpen: (state) => state.isEditDialogOpen,
    getIsDeleteDialogOpen: (state) => state.isDeleteDialogOpen,
    getEditedItem: (state) => state.editedItem,
  },
  modules: {
  }
})
