import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import { useUserStore } from "@/stores/userStore.js";
import { storeToRefs } from "pinia";

const onlyAuthUser = async (to, from, next) => {

  const userStore = useUserStore();
  const { loginUser, isLogin } = storeToRefs(userStore);
  const { isValidToken } = userStore;

  isValidToken();

  if (isLogin.value) {
    next();
  } else {
    next({name: 'home'});
  }
};

const onlyAdmin = async (to, from, next) => {
  const userStore = useUserStore();
  const { loginUser, isLogin } = storeToRefs(userStore);
  const { isValidToken } = userStore;

  isValidToken();

  if (isLogin.value && loginUser.value.role === "ROLE_ADMIN") {
    next();
  } else {
    next({name: 'home'});
  }
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/mypage",
      name: "mypage",
      beforeEnter: onlyAuthUser,
      component: () => import("@/views/MyPageView.vue"),
    },
    {
      path: "/wod",
      name: "random-wod",
      beforeEnter: onlyAuthUser,
      component: () => import("@/views/RandomWodView.vue"),
    },
    {
      path: "/admin",
      name: "admin",
      beforeEnter: onlyAdmin,
      component: () => import("@/views/AdminView.vue")
    }
  ],
});

export default router;
