<template>
    <div>
      <h1>회원 목록</h1>
      <ul>
        <li v-for="member in members" :key="member.id">
          <router-link :to="{ name: 'MemberDetail', params: { id: member.id } }">
            {{ member.name }} - {{ member.email }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['members']),
    },
    methods: {
      ...mapActions(['fetchMembers']),
    },
    created() {
      this.fetchMembers();
    },
  };
  </script>
  