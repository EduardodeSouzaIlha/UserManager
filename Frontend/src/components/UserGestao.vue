<template>
  <div>
    <user-list v-if="!showAddForm && !loading" :users="users" @show-add-user-form="showAddUserForm" />
    <add-user-form v-if="showAddForm" @cancel="hideAddUserForm" @user-added="handleUserAdded"/>

    <div v-if="loading" class="loading">
      Carregando...
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import UserList from './UserList.vue';
import AddUserForm from './AddUserForm.vue';

export default {
  name: 'UserManagement',
  components: {
    UserList,
    AddUserForm
  },
  data() {
    return {
      users: [], 
      showAddForm: false,
      loading: false 
    };
  },
  methods: {
    async getUsers() {
      try {
        this.loading = true;
        const response = await axios.get(process.env.VUE_APP_API_URL+'/user');
        this.users = response.data;
        console.log('Usuários carregados:', this.users);
      } catch (error) {
        console.error('Erro ao carregar usuários:', error);
      } finally {
        this.loading = false; 
      }
    },   
    showAddUserForm() {
   
      this.showAddForm = true;
    },
    hideAddUserForm() {
      this.showAddForm = false;
    },
    handleUserAdded(newUser) {
      this.users.push(newUser);


      this.showAddForm = false;
    }
  },
  mounted() {
    this.getUsers();
  }
};
</script>

<style>
.loading {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  font-size: 1.2em;
}
</style>
