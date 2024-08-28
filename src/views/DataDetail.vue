<template>
    <div>
      <h1>{{ dataroom.title }}</h1>
      <p>{{ dataroom.content }}</p>
      <p>작성자: {{ dataroom.author }} | 조회수: {{ dataroom.hits }} | 작성일: {{ dataroom.resdate }}</p>
      
      <div v-if="dataroom.datafile">
        <h3>첨부파일:</h3>
        <a :href="fileUrl" download>{{ dataroom.datafile }}</a>
      </div>
      
      <button @click="goToUpdate">자료 수정</button>
      <button @click="deleteData">자료 삭제</button>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['dataroom']),
      fileUrl() {
        return `http://localhost:8085/uploads/${this.dataroom.datafile}`;
      },
    },
    methods: {
      ...mapActions(['fetchDataroom', 'deleteDataroom']),
      goToUpdate() {
        this.$router.push({ name: 'DataUpdate', params: { id: this.dataroom.dno } });
      },
      deleteData() {
        this.deleteDataroom(this.dataroom.dno).then(() => {
          this.$router.push({ name: 'DataList' });
        });
      },
    },
    created() {
      this.fetchDataroom(this.$route.params.id);
    },
  };
  </script>
  