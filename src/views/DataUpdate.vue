<template>
    <div>
      <h1>자료 수정</h1>
      <form @submit.prevent="submitForm">
        <div>
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
        <div>
          <label for="datafile">파일 업로드:</label>
          <input type="file" @change="handleFileUpload" id="datafile" />
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
          datafile: null,
        },
      };
    },
    computed: {
      ...mapState(['dataroom']),
    },
    methods: {
      ...mapActions(['fetchDataroom', 'updateDataroom']),
      handleFileUpload(event) {
        this.form.datafile = event.target.files[0];
      },
      submitForm() {
        const formData = new FormData();
        formData.append('title', this.form.title);
        formData.append('content', this.form.content);
        formData.append('author', this.form.author);
        if (this.form.datafile) {
          formData.append('datafile', this.form.datafile);
        }
  
        this.updateDataroom({ id: this.$route.params.id, formData }).then(() => {
          this.$router.push({ name: 'DataDetail', params: { id: this.dataroom.dno } });
        });
      },
    },
    created() {
      this.fetchDataroom(this.$route.params.id).then(() => {
        this.form = {
          title: this.dataroom.title,
          content: this.dataroom.content,
          author: this.dataroom.author,
        };
      });
    },
  };
  </script>
  