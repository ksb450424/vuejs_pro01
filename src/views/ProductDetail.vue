<template>
    <div>
      <h1>{{ product.pname }}</h1>
      <p>카테고리: {{ product.cate }}</p>
      <p>{{ product.pcontent }}</p>
      <p>조회수: {{ product.hits }} | 작성일: {{ product.resdate }}</p>
  
      <div v-if="product.img1 || product.img2 || product.img3">
        <h3>이미지:</h3>
        <img v-if="product.img1" :src="imgUrl(product.img1)" alt="Image 1" />
        <img v-if="product.img2" :src="imgUrl(product.img2)" alt="Image 2" />
        <img v-if="product.img3" :src="imgUrl(product.img3)" alt="Image 3" />
      </div>
  
      <button @click="goToUpdate">제품 수정</button>
      <button @click="deleteProduct">제품 삭제</button>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['product']),
    },
    methods: {
      ...mapActions(['fetchProduct', 'deleteProduct']),
      imgUrl(img) {
        return `http://localhost:8085/uploads/${img}`;
      },
      goToUpdate() {
        this.$router.push({ name: 'ProductUpdate', params: { id: this.product.pno } });
      },
      deleteProduct() {
        this.deleteProduct(this.product.pno).then(() => {
          this.$router.push({ name: 'ProductList' });
        });
      },
    },
    created() {
      this.fetchProduct(this.$route.params.id);
    },
  };
  </script>
  