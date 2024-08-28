<template>
    <div>
      <h1>답변 등록</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="content">답변 내용:</label>
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
          content: '',
          author: '',
          lev: 1, // 답변의 레벨을 1로 설정
          parno: this.$route.params.id, // 질문의 번호를 부모 번호로 설정
        },
      };
    },
    methods: {
      ...mapActions(['insertAnswer']),
      submitForm() {
        this.insertAnswer(this.form).then(() => {
          this.$router.push({ name: 'QnaDetail', params: { id: this.form.parno } });
        });
      },
    },
  };
  </script>
  