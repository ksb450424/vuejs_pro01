<template>
    <div>
      <h1>마이페이지</h1>
      <p>이름: {{ member.name }}</p>
      <p>아이디: {{ member.id }}</p>
      <p>생일: {{ member.birth }}</p>
      <p>이메일: {{ member.email }}</p>
      <p>전화번호: {{ member.tel }}</p>
      <p>주소: {{ member.addr1 }} {{ member.addr2 }}</p>
      <p>우편번호: {{ member.postcode }}</p>
  
      <button @click="goToUpdate">회원 정보 수정</button>
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
      // 로그인된 회원의 ID를 가져와서 회원 정보를 불러옵니다.
      const userId = 'alice'; // 실제 로그인 상태에 따라 동적으로 변경해야 합니다.
      this.fetchMember(userId);
    },
  };
  </script>
  