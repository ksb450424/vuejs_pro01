import axios from 'axios';

const API_BASE_URL = 'http://localhost:8085';

export default {
  async getList() {
    const response = await axios.get(`${API_BASE_URL}/boards/list`);
    return response.data;
  },
  async getDetail(id) {
    const response = await axios.get(`${API_BASE_URL}/boards/detail/${id}`);
    return response.data;
  },
  async insertBoard(board) {
    const response = await axios.post(`${API_BASE_URL}/boards/insert`, board);
    return response.data;
  },
  async updateBoard(id, board) {
    const response = await axios.put(`${API_BASE_URL}/boards/update/${id}`, board);
    return response.data;
  },
  async deleteBoard(id) {
    const response = await axios.delete(`${API_BASE_URL}/boards/delete/${id}`);
    return response.data;
  }
};