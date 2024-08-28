<template>
    <div>
      <h1>{{ board.title }}</h1>
      <p>{{ board.content }}</p>
      <button @click="goToUpdate">수정</button>
      <button @click="deleteBoard">삭제</button>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['board']),
    },
    methods: {
      ...mapActions(['fetchBoard', 'deleteBoard']),
      goToUpdate() {
        this.$router.push({ name: 'BoardUpdate', params: { id: this.board.no } });
      },
      deleteBoard() {
        this.deleteBoard(this.board.no).then(() => {
          this.$router.push({ name: 'BoardList' });
        });
      },
    },
    created() {
      this.fetchBoard(this.$route.params.id);
    },
  };
  </script>