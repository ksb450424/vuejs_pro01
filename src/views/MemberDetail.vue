<template>
    <div>
      <h1>회원 상세 보기</h1>
      <p>이름: {{ member.name }}</p>
      <p>아이디: {{ member.id }}</p>
      <p>생일: {{ member.birth }}</p>
      <p>이메일: {{ member.email }}</p>
      <p>전화번호: {{ member.tel }}</p>
      <p>주소: {{ member.addr1 }} {{ member.addr2 }}</p>
      <p>우편번호: {{ member.postcode }}</p>
  
      <button @click="goToUpdate">회원 수정</button>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['member']),
    },
    methods: {
      ...mapActions(['fetchMember']),
      goToUpdate() {
        this.$router.push({ name: 'MemberUpdate', params: { id: this.member.id } });
      },
    },
    created() {
      this.fetchMember(this.$route.params.id);
    },
  };
  </script>
  