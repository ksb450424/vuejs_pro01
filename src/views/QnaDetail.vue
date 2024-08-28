<template>
    <div>
      <h1>{{ qna.title }}</h1>
      <p>{{ qna.content }}</p>
      <p>작성자: {{ qna.author }} | 조회수: {{ qna.hits }} | 작성일: {{ qna.resdate }}</p>
      <button @click="goToUpdate" v-if="isQuestion">질문 수정</button>
      <button @click="deleteQna" v-if="isQuestion">질문 삭제</button>
  
      <h2>답변</h2>
      <div v-if="answers.length">
        <div v-for="answer in answers" :key="answer.qno">
          <p>{{ answer.content }}</p>
          <p>작성자: {{ answer.author }} | 작성일: {{ answer.resdate }}</p>
          <button @click="goToAnswerUpdate(answer.qno)">답변 수정</button>
        </div>
      </div>
      <div v-else>
        <button @click="goToAnswerInsert">답변 등록</button>
      </div>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['qna', 'answers']),
      isQuestion() {
        return this.qna.lev === 0;
      },
    },
    methods: {
      ...mapActions(['fetchQna', 'fetchAnswers', 'deleteQna']),
      goToUpdate() {
        this.$router.push({ name: 'QnaUpdate', params: { id: this.qna.qno } });
      },
      deleteQna() {
        this.deleteQna(this.qna.qno).then(() => {
          this.$router.push({ name: 'QnaList' });
        });
      },
      goToAnswerInsert() {
        this.$router.push({ name: 'AnswerInsert', params: { id: this.qna.qno } });
      },
      goToAnswerUpdate(answerId) {
        this.$router.push({ name: 'QnaUpdate', params: { id: answerId } });
      },
    },
    created() {
      const qno = this.$route.params.id;
      this.fetchQna(qno);
      this.fetchAnswers(qno);
    },
  };
  </script>
  