<template>
    <div>
      <h1>{{ isQuestion ? '질문' : '답변' }} 수정</h1>
      <form @submit.prevent="submitForm">
        <div v-if="isQuestion">
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
      ...mapState(['qna']),
      isQuestion() {
        return this.qna.lev === 0;
      },
    },
    methods: {
      ...mapActions(['fetchQna', 'updateQna']),
      submitForm() {
        this.updateQna({ id: this.$route.params.id, qna: this.form }).then(() => {
          const targetRoute = this.isQuestion ? 'QnaDetail' : 'QnaDetail';
          this.$router.push({ name: targetRoute, params: { id: this.qna.lev === 0 ? this.qna.qno : this.qna.parno } });
        });
      },
    },
    created() {
      this.fetchQna(this.$route.params.id).then(() => {
        this.form = {
          title: this.qna.title,
          content: this.qna.content,
          author: this.qna.author,
        };
      });
    },
  };
  </script>
  