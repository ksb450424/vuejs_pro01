<template>
    <div>
      <h1>제품 목록</h1>
      <button @click="goToInsert">제품 등록</button>
      <ul>
        <li v-for="product in products" :key="product.pno">
          <router-link :to="{ name: 'ProductDetail', params: { id: product.pno } }">
            {{ product.pname }} - {{ product.cate }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['products']),
    },
    methods: {
      ...mapActions(['fetchProducts']),
      goToInsert() {
        this.$router.push({ name: 'ProductInsert' });
      },
    },
    created() {
      this.fetchProducts();
    },
  };
  </script>
  