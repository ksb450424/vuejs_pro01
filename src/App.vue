<template>
  <div id="app" class="min-h-screen bg-gray-100">
    <!-- 상단 메뉴 -->
    <header class="bg-gray-800 text-white">
      <nav class="container mx-auto flex justify-between items-center p-4">
        <!-- 로고 또는 애플리케이션 이름 -->
        <div class="text-xl font-bold">
          <router-link to="/" class="text-white hover:text-gray-300">01_company_mvvm</router-link>
        </div>
        
        <!-- 메뉴 -->
        <div>
          <ul class="flex space-x-4">
            <li><router-link to="/" class="hover:text-gray-300">Home</router-link></li>
            <li><router-link to="/products" class="hover:text-gray-300">Product</router-link></li>
            <li><router-link to="/boards" class="hover:text-gray-300">Board</router-link></li>
            <li><router-link to="/qnas" class="hover:text-gray-300">Qna</router-link></li>
            <li><router-link to="/datarooms" class="hover:text-gray-300">Data</router-link></li>
            <li><router-link to="/contact" class="hover:text-gray-300">Contact</router-link></li>
          </ul>
        </div>

        <!-- 로그인 상태에 따른 메뉴 -->
        <div>
          <ul class="flex space-x-4">
            <template v-if="!isLoggedIn">
              <li><router-link to="/members/login" class="hover:text-gray-300">Login</router-link></li>
              <li><router-link to="/members/join" class="hover:text-gray-300">Join</router-link></li>
            </template>
            <template v-else>
              <li><router-link :to="{ name: 'MyPage', params: { id: userId } }" class="hover:text-gray-300">MyPage</router-link></li>
              <li><a @click.prevent="logout" class="hover:text-gray-300">Logout</a></li>
            </template>
          </ul>
        </div>
      </nav>
    </header>

    <!-- 메인 콘텐츠 -->
    <main class="container mx-auto p-4">
      <router-view />
    </main>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['member']),
    isLoggedIn() {
      return !!this.member; // 사용자가 로그인했는지 여부를 판단
    },
    userId() {
      return this.member?.id || ''; // 로그인된 사용자 ID
    }
  },
  methods: {
    logout() {
      // 로그아웃 로직
      this.$store.dispatch('logout').then(() => {
        this.$router.push({ name: 'Login' });
      });
    }
  }
}
</script>

<style scoped>
/* Add any additional custom styles here */
</style>
