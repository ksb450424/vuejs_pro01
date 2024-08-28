<template>
    <div>
      <h1>Q&A 목록</h1>
      <button @click="goToInsert">질문 등록</button>
      <ul>
        <li v-for="qna in qnas" :key="qna.qno">
          <router-link :to="{ name: 'QnaDetail', params: { id: qna.qno } }">
            {{ qna.title }} - {{ qna.author }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['qnas']),
    },
    methods: {
      ...mapActions(['fetchQnas']),
      goToInsert() {
        this.$router.push({ name: 'QnaInsert' });
      },
    },
    created() {
      this.fetchQnas();
    },
  };
  </script>
  