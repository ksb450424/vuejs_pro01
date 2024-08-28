<template>
    <div>
      <h1>자료 목록</h1>
      <button @click="goToInsert">자료 등록</button>
      <ul>
        <li v-for="data in datarooms" :key="data.dno">
          <router-link :to="{ name: 'DataDetail', params: { id: data.dno } }">
            {{ data.title }} - {{ data.author }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['datarooms']),
    },
    methods: {
      ...mapActions(['fetchDatarooms']),
      goToInsert() {
        this.$router.push({ name: 'DataInsert' });
      },
    },
    created() {
      this.fetchDatarooms();
    },
  };
  </script>
  