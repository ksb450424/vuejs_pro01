<template>
    <div>
      <h1>제품 수정</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="cate">카테고리:</label>
          <input type="text" v-model="form.cate" id="cate" required />
        </div>
        <div>
          <label for="pname">제품명:</label>
          <input type="text" v-model="form.pname" id="pname" required />
        </div>
        <div>
          <label for="pcontent">내용:</label>
          <textarea v-model="form.pcontent" id="pcontent" required></textarea>
        </div>
        <div>
          <label for="img1">이미지 1:</label>
          <input type="file" @change="handleFileUpload(1, $event)" id="img1" />
        </div>
        <div>
          <label for="img2">이미지 2:</label>
          <input type="file" @change="handleFileUpload(2, $event)" id="img2" />
        </div>
        <div>
          <label for="img3">이미지 3:</label>
          <input type="file" @change="handleFileUpload(3, $event)" id="img3" />
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
          cate: '',
          pname: '',
          pcontent: '',
          img1: null,
          img2: null,
          img3: null,
        },
      };
    },
    computed: {
      ...mapState(['product']),
    },
    methods: {
      ...mapActions(['fetchProduct', 'updateProduct']),
      handleFileUpload(index, event) {
        this.form[`img${index}`] = event.target.files[0];
      },
      submitForm() {
        const formData = new FormData();
        formData.append('cate', this.form.cate);
        formData.append('pname', this.form.pname);
        formData.append('pcontent', this.form.pcontent);
        if (this.form.img1) formData.append('img1', this.form.img1);
        if (this.form.img2) formData.append('img2', this.form.img2);
        if (this.form.img3) formData.append('img3', this.form.img3);
  
        this.updateProduct({ id: this.$route.params.id, formData }).then(() => {
          this.$router.push({ name: 'ProductDetail', params: { id: this.product.pno } });
        });
      },
    },
    created() {
      this.fetchProduct(this.$route.params.id).then(() => {
        this.form = {
          cate: this.product.cate,
          pname: this.product.pname,
          pcontent: this.product.pcontent,
        };
      });
    },
  };
  </script>
  