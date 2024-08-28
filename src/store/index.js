import { createStore } from 'vuex';
import axios from 'axios';

const store = createStore({
  state() {
    return {
      members: [],
      member: null,
      products: [],
      product: null,
      boards: [],
      board: null,
      qnas: [],
      qna: null,
      datarooms: [],
      dataroom: null,
    };
  },
  mutations: {
    setMembers(state, members) {
      state.members = members;
    },
    setMember(state, member) {
      state.member = member;
    },
    setProducts(state, products) {
      state.products = products;
    },
    setProduct(state, product) {
      state.product = product;
    },
    setBoards(state, boards) {
      state.boards = boards;
    },
    setBoard(state, board) {
      state.board = board;
    },
    setQnas(state, qnas) {
      state.qnas = qnas;
    },
    setQna(state, qna) {
      state.qna = qna;
    },
    setDatarooms(state, datarooms) {
      state.datarooms = datarooms;
    },
    setDataroom(state, dataroom) {
      state.dataroom = dataroom;
    },
  },
  actions: {
    // 회원 관련
    async fetchMembers({ commit }) {
      const response = await axios.get('http://localhost:8085/members/list');
      commit('setMembers', response.data);
    },
    async fetchMember({ commit }, id) {
      const response = await axios.get(`http://localhost:8085/members/detail/${id}`);
      commit('setMember', response.data);
    },
    async register({ dispatch }, member) {
      await axios.post('http://localhost:8085/members/join', member);
      dispatch('fetchMembers'); // 새로 등록된 회원 목록을 업데이트
    },
    async login(_, credentials) {
      await axios.post('http://localhost:8085/members/login', credentials);
      // 로그인 성공 시 추가 작업을 여기서 처리할 수 있습니다.
    },
    async checkId(_, id) {
      const response = await axios.get(`http://localhost:8085/members/confirm/${id}`);
      return response.data; // 사용 가능한 아이디인지 여부를 반환합니다.
    },

    // 제품 관련
    async fetchProducts({ commit }) {
      const response = await axios.get('http://localhost:8085/products/list');
      commit('setProducts', response.data);
    },
    async fetchProduct({ commit }, id) {
      const response = await axios.get(`http://localhost:8085/products/detail/${id}`);
      commit('setProduct', response.data);
    },
    async insertProduct({ dispatch }, product) {
      await axios.post('http://localhost:8085/products/insert', product);
      dispatch('fetchProducts');
    },
    async updateProduct({ dispatch }, { id, product }) {
      await axios.put(`http://localhost:8085/products/update/${id}`, product);
      dispatch('fetchProduct', id);
    },
    async deleteProduct({ dispatch }, id) {
      await axios.delete(`http://localhost:8085/products/delete/${id}`);
      dispatch('fetchProducts');
    },

    // 게시글 관련
    async fetchBoards({ commit }) {
      const response = await axios.get('http://localhost:8085/boards/list');
      commit('setBoards', response.data);
    },
    async fetchBoard({ commit }, id) {
      const response = await axios.get(`http://localhost:8085/boards/detail/${id}`);
      commit('setBoard', response.data);
    },
    async insertBoard({ dispatch }, board) {
      await axios.post('http://localhost:8085/boards/insert', board);
      dispatch('fetchBoards');
    },
    async updateBoard({ dispatch }, { id, board }) {
      await axios.put(`http://localhost:8085/boards/update/${id}`, board);
      dispatch('fetchBoard', id);
    },
    async deleteBoard({ dispatch }, id) {
      await axios.delete(`http://localhost:8085/boards/delete/${id}`);
      dispatch('fetchBoards');
    },

    // Q&A 관련
    async fetchQnas({ commit }) {
      const response = await axios.get('http://localhost:8085/qna/list');
      commit('setQnas', response.data);
    },
    async fetchQna({ commit }, id) {
      const response = await axios.get(`http://localhost:8085/qna/detail/${id}`);
      commit('setQna', response.data);
    },
    async insertQna({ dispatch }, qna) {
      await axios.post('http://localhost:8085/qna/insertQuestion', qna);
      dispatch('fetchQnas');
    },
    async updateQna({ dispatch }, { id, qna }) {
      await axios.put(`http://localhost:8085/qna/update/${id}`, qna);
      dispatch('fetchQna', id);
    },
    async deleteQna({ dispatch }, id) {
      await axios.delete(`http://localhost:8085/qna/delete/${id}`);
      dispatch('fetchQnas');
    },

    // 자료실 관련
    async fetchDatarooms({ commit }) {
      const response = await axios.get('http://localhost:8085/dataroom/list');
      commit('setDatarooms', response.data);
    },
    async fetchDataroom({ commit }, id) {
      const response = await axios.get(`http://localhost:8085/dataroom/detail/${id}`);
      commit('setDataroom', response.data);
    },
    async insertDataroom({ dispatch }, dataroom) {
      await axios.post('http://localhost:8085/dataroom/upload', dataroom);
      dispatch('fetchDatarooms');
    },
    async updateDataroom({ dispatch }, { id, dataroom }) {
      await axios.put(`http://localhost:8085/dataroom/update/${id}`, dataroom);
      dispatch('fetchDataroom', id);
    },
    async deleteDataroom({ dispatch }, id) {
      await axios.delete(`http://localhost:8085/dataroom/delete/${id}`);
      dispatch('fetchDatarooms');
    },
  },
});

export default store;
