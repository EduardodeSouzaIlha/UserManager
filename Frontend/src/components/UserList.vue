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
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.name }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.bio }}</td>
              <td>{{ user.gender.gender }}</td>
              <td>{{ user.confirmed }}</td>
              <td>
                <button @click="editUser(user.id)">Editar</button>
                <button @click="deleteUser(user.id)">Excluir</button>
              </td>
            </tr>
          </tbody>
        </table>
        <button @click="addUser">Adicionar Usuário</button>
      </div>
    </div>
</template>
<script>


export default {
  name: 'UserList',
  props: {
    users: {
      type: Array,
      required: true
    }
  },
  methods: {   
    addUser() {
      this.$emit('show-add-user-form');
    },
    editUser(id) {
    },
    deleteUser(id) {
    }
  }
};
</script>

<style>
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
