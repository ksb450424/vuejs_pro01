<template>
    <div>
      <h1>글 수정</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="title">제목:</label>
          <input type="text" v-model="form.title" id="title" required />
        </div>
        <div>
          <label for="content">내용:</label>
          <textarea v-model="form.content" id="content" required></textarea>
        </div>
        <div>
          <label for="author">작성자:</label>
          <input type="text" v-model="form.author" id="author" required />
        </div>
        <button type="submit">수정</button>
      </form>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    data() {
      return {
        form: {
          title: '',
          content: '',
          author: '',
        },
      };
    },
    computed: {
      ...mapState(['board']),
    },
    methods: {
      ...mapActions(['fetchBoard', 'updateBoard']),
      submitForm() {
        this.updateBoard({ id: this.$route.params.id, board: this.form }).then(() => {
          this.$router.push({ name: 'BoardDetail', params: { id: this.$route.params.id } });
        });
      },
    },
    created() {
      this.fetchBoard(this.$route.params.id).then(() => {
        this.form = {
          title: this.board.title,
          content: this.board.content,
          author: this.board.author,
        };
      });
    },
  };
  </script>
  