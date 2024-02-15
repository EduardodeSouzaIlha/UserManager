<template>
  <div class="user-details">
    <h2>Detalhes do Usuário</h2>
    <div class="details">
      <p><strong>ID:</strong> {{ user.id }}</p>
      <p><strong>Nome:</strong> {{ user.name }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
      <p><strong>Biografia:</strong> {{ user.bio }}</p>
      <p><strong>Gênero:</strong> {{ user.gender.gender }}</p>
      <p><strong>Confirmado:</strong> {{ user.confirmed }}</p>
      <button @click="cancel" class="cancel-button">Cancelar</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  methods: {
    cancel() {
      this.$emit('cancel');
    }
  }
};
</script>

<style scoped>
.user-details {
  font-family: Arial, sans-serif;
  max-width: 600px;
  margin: auto;
  padding: 20px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.user-details h2 {
  color: #333;
  margin-bottom: 20px;
}

.user-details .details {
  margin-top: 20px;
}

.user-details .details p {
  margin-bottom: 10px;
}

.user-details .cancel-button {
  background-color: #f44336;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.user-details .cancel-button:hover {
  background-color: #d32f2f;
}
</style>
