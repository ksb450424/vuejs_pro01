<template>
    <div>
      <h1>자료 등록</h1>
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
        <button type="submit">등록</button>
      </form>
    </div>
  </template>
  
  <script>
  import { mapActions } from 'vuex';
  
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
    methods: {
      ...mapActions(['insertDataroom']),
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
  
        this.insertDataroom(formData).then(() => {
          this.$router.push({ name: 'DataList' });
        });
      },
    },
  };
  </script>
  