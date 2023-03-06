<template>
  <div class="btn-group">
    <button class="btn btn-google" @click='googleLogin'>Google로 시작하기</button>
  </div>
</template>

<script>
export default {
  name: "OAuth2Login",
  methods: {
    async googleLogin() {
      const prevUrl = sessionStorage.getItem('prev_url') || '/'
      const callbackUrl = `${window.location.origin}/set-token?prev_url=${prevUrl}`
      window.location = `http://localhost:8000/oauth2/authorization/google?callback_url=${callbackUrl}`
    }
  }
}
</script>

<style scoped>
.btn-group {
  width: 100%;
}

.btn-google {
  width: 100%;
  background-color: white;
  border: solid 1px #ccc;
  text-align: center;
  background-image: url("https://vendor.imweb.me/images/google_icon.png");
  background-repeat: no-repeat;
  background-size: 20px;
  background-position: 15px 50%;
}

.btn {
  padding: 11px 12px;
  font-size: 16px;
  cursor: pointer;
  border-radius: 4px;
}
</style>
