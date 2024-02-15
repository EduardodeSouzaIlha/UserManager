<template>
  <div>
    
    <user-list 
      v-if="!showAddForm && !loading && !showUserDetailsFlag" 
      :users="users" 
      @show-add-user-form="showAddUserForm" 
      @user-deleted="handleUserDeleted" 
      @edit-user="editUser"
      @user-selected="showUserDetails"
    />
    <add-user-form 
      v-if="showAddForm || editingUser" 
      @cancel="cancelAddUser" 
      @user-added="handleUserAdded" 
      @user-updated="handleUserUpdated"
      :editing="editingUser" 
      :user-data="userDataToEdit" 
    />
    <user-details 
      v-if="showUserDetailsFlag" 
      :user="selectedUser" 
      @cancel="cancelShowUserDetails" 
    />
    <div v-if="loading" class="loading">
      Carregando...
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import UserList from './UserList.vue';
import AddUserForm from './AddUserForm.vue';
import UserDetails from './UserDetails.vue';

export default {
  name: 'UserManagement',
  components: {
    UserList,
    AddUserForm,
    UserDetails
  },
  data() {
    return {
      users: [], 
      showAddForm: false,
      loading: false,
      editingUser: false,
      userDataToEdit: null,
      showUserDetailsFlag: false,
      selectedUser: null
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
    cancelAddUser() {
      this.showAddForm = false;
      this.editingUser = false; 
      this.userDataToEdit = null; 
    },
    handleUserAdded(newUser) {
      this.users.push(newUser);
      this.showAddForm = false;
      this.editingUser = false;
      this.userDataToEdit = null; 
    },
    handleUserDeleted(userId) {
      this.users = this.users.filter(user => user.id !== userId);
    },
    handleUserUpdated() {
      this.getUsers();
      this.showAddForm = false;
      this.editingUser = false;
    },
    editUser(userData) {
      this.userDataToEdit = userData;
      this.editingUser = true;
      this.showAddForm = true;
    },
    showUserDetails(user) {
      this.showUserDetailsFlag = true;
      this.selectedUser = user;
    },
    cancelShowUserDetails() {
      this.showUserDetailsFlag = false;
      this.selectedUser = null;
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
