<template>
    <div>
      <h1>Board List</h1>
      <button @click="goToInsert">글 등록</button>
      <ul>
        <li v-for="board in boards" :key="board.no">
          <router-link :to="{ name: 'BoardDetail', params: { id: board.no } }">
            {{ board.title }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['boards']),
    },
    methods: {
      ...mapActions(['fetchBoards']),
      goToInsert() {
        this.$router.push({ name: 'BoardInsert' });
      },
    },
    created() {
      this.fetchBoards();
    },
  };
  </script>
  