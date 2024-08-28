<template>
    <div>
      <h1>글 등록</h1>
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
        <button type="submit">등록</button>
      </form>
    </div>
  </template>
  
  <script>
  import { mapActions } from 'vuex';
  
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
    methods: {
      ...mapActions(['createBoard']),
      submitForm() {
        this.createBoard(this.form).then(() => {
          this.$router.push({ name: 'BoardList' });
        });
      },
    },
  };
  </script>
  