<template>
  <div>
    <h2>Lista de Usuários</h2>
    <div>
      <table class="center table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Bio</th>
            <th>Gender</th>
            <th>Confirmed</th>
            <th colspan="2">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id" @click="showUserDetails(user)" class="user-row">
            <td>{{ user.id }}</td>
            <td>{{ user.name }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.bio.length > 12 ? user.bio.substring(0, 12) + '...' : user.bio }}</td>
            <td>{{ user.gender.gender }}</td>
            <td>{{ user.confirmed }}</td>
            <td @click.stop >
              <button class="primary-button" @click="editUser(user)">Editar</button>
            </td>
            <td @click.stop>
              <button class="secondary-button" @click="showConfirmation(user.id)">Excluir</button>
            </td>
          </tr>
        </tbody>
      </table>
      <button class="primary-button" @click="addUser">Adicionar Usuário</button>
    </div>

    <!-- Modal de confirmação -->
    <div v-if="showConfirmationModal" class="modal">
      <div class="modal-content">
        <p>Tem certeza que deseja excluir este usuário?</p>
        <button class="primary-button" @click="deleteUserConfirmed">Confirmar</button>
        <button class="secondary-button" @click="hideConfirmation">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserList',
  props: {
    users: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      showConfirmationModal: false,
      userIdToDelete: null
    };
  },
  methods: {
    addUser() {
      this.$emit('show-add-user-form');
    },
    editUser(user) {
      this.$emit('edit-user', user);
    },
    showConfirmation(userId) {
      this.userIdToDelete = userId;
      this.showConfirmationModal = true;
    },
    hideConfirmation() {
      this.showConfirmationModal = false;
      this.userIdToDelete = null;
    },
    deleteUserConfirmed() {
      const url = process.env.VUE_APP_API_URL + `/user/${this.userIdToDelete}`;
      axios.delete(url)
        .then(response => {
          if (response.status === 200) {
            this.$emit('user-deleted', this.userIdToDelete);
          }
        })
        .catch(error => {
          console.error('Erro ao excluir usuário:', error);
        })
        .finally(() => {
          this.hideConfirmation();
        });
    },
    showUserDetails(user) {
      this.$emit('user-selected', user);
    }
  }
};
</script>

<style scoped>
.action-cell {
  text-align: center;
}

.primary-button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.secondary-button {
  background-color: #f44336;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.primary-button:hover {
  background-color: #45a049;
}

.secondary-button:hover {
  background-color: #db2d20;
}

.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 40%;
}

.modal-content p {
  margin-bottom: 20px;
}

.user-row:hover {
  background-color: #f5f5f5;
  cursor: pointer;
}

table.center {
  margin-left: auto;
  margin-right: auto;
}

.table-bordered {
  border-collapse: collapse;
}

.table-bordered th,
.table-bordered td {
  border: 1px solid #000;
  padding: 8px;
}
</style>
