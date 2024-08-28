import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import ProductList from '../views/ProductList.vue';
import BoardList from '../views/BoardList.vue';
import QnaList from '../views/QnaList.vue';
import DataList from '../views/DataList.vue';
import Contact from '../views/Contact.vue';
import MemberList from '../views/MemberList.vue';
import MemberDetail from '../views/MemberDetail.vue';
import MyPage from '../views/MyPage.vue';
import Join from '../views/Join.vue';
import Login from '../views/Login.vue';
import ProductDetail from '../views/ProductDetail.vue';
import ProductInsert from '../views/ProductInsert.vue';
import ProductUpdate from '../views/ProductUpdate.vue';
import BoardDetail from '../views/BoardDetail.vue';
import BoardInsert from '../views/BoardInsert.vue';
import BoardUpdate from '../views/BoardUpdate.vue';
import QnaDetail from '../views/QnaDetail.vue';
import QnaInsert from '../views/QnaInsert.vue';
import QnaUpdate from '../views/QnaUpdate.vue';
import DataDetail from '../views/DataDetail.vue';
import DataInsert from '../views/DataInsert.vue';
import DataUpdate from '../views/DataUpdate.vue';

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/products', name: 'ProductList', component: ProductList },
  { path: '/products/detail/:id', name: 'ProductDetail', component: ProductDetail, props: true },
  { path: '/products/insert', name: 'ProductInsert', component: ProductInsert },
  { path: '/products/update/:id', name: 'ProductUpdate', component: ProductUpdate, props: true },
  { path: '/boards', name: 'BoardList', component: BoardList },
  { path: '/boards/detail/:id', name: 'BoardDetail', component: BoardDetail, props: true },
  { path: '/boards/insert', name: 'BoardInsert', component: BoardInsert },
  { path: '/boards/update/:id', name: 'BoardUpdate', component: BoardUpdate, props: true },
  { path: '/qnas', name: 'QnaList', component: QnaList },
  { path: '/qnas/detail/:id', name: 'QnaDetail', component: QnaDetail, props: true },
  { path: '/qnas/insert', name: 'QnaInsert', component: QnaInsert },
  { path: '/qnas/update/:id', name: 'QnaUpdate', component: QnaUpdate, props: true },
  { path: '/datarooms', name: 'DataList', component: DataList },
  { path: '/datarooms/detail/:id', name: 'DataDetail', component: DataDetail, props: true },
  { path: '/datarooms/upload', name: 'DataInsert', component: DataInsert },
  { path: '/datarooms/update/:id', name: 'DataUpdate', component: DataUpdate, props: true },
  { path: '/contact', name: 'Contact', component: Contact },
  { path: '/members/list', name: 'MemberList', component: MemberList },
  { path: '/members/detail/:id', name: 'MemberDetail', component: MemberDetail, props: true },
  { path: '/members/mypage/:id', name: 'MyPage', component: MyPage, props: true },
  { path: '/members/join', name: 'Join', component: Join },
  { path: '/members/login', name: 'Login', component: Login },
  { path: '/members/logout', name: 'Logout', beforeEnter: (to, from, next) => {
    // 로그아웃 처리 로직
    next({ name: 'Login' });
  }},
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
