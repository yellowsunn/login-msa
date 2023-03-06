<template>
  <div class="login">
    <h1>회원가입/로그인</h1>
    <OAuth2Login/>
  </div>
</template>

<script>
import OAuth2Login from "@/components/OAuth2Login.vue";

export default {
  name: "LoginView",
  components: {OAuth2Login}
}
</script>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  width: 500px;
  padding-top: 200px;
}

@media screen and (max-width: 720px) {
  .login {
    width: 90%;
  }
}
</style>
