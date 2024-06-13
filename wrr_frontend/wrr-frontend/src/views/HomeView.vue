<script setup>
import { ref } from "vue";

const dialogBackdrop = ref(null);

const openDialog = () => {
  dialogBackdrop.value.classList.remove("pointer-events-none", "opacity-0");
  dialogBackdrop.value.classList.add("pointer-events-auto", "opacity-100");
};

const closeDialog = () => {
  dialogBackdrop.value.classList.remove("pointer-events-auto", "opacity-100");
  dialogBackdrop.value.classList.add("pointer-events-none", "opacity-0");
};
</script>
<template>
  <div class="px-6 mx-auto grid place-items-center text-center">
    <div class="mt-10 flex flex-col items-center">
      <img
        src="/src/assets/logo/wrr.png"
        alt="WRR Logo"
        style="width: 20%; height: auto"
      />
      <h1
        class="block antialiased tracking-normal font-mono text-5xl font-bold leading-tight text-blue-gray-900 mt-7 !text-2xl md:text-3xl max-w-xl mx-auto !leading-snug"
      >
        We're currently undergoing maintenance to improve your experience.
      </h1>
      <p
        class="block antialiased font-mono text-base font-light leading-relaxed text-inherit !mt-4 md:max-w-2xl text-[18px] font-normal text-gray-500"
      >
        Please bear with us while we make these enhancements. We'll be back
        shortly. Thank you for your patience!
      </p>
      <button
        class="mt-3 select-none rounded-lg bg-gray-900 py-3 px-6 text-center align-middle font-mono text-xs font-bold uppercase text-white shadow-md shadow-gray-900/10 transition-all hover:shadow-lg hover:shadow-gray-900/20 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
        type="button"
        @click="openDialog"
      >
        Sign in
      </button>
    </div>
    <div
      ref="dialogBackdrop"
      class="pointer-events-none fixed inset-0 z-[999] grid h-screen w-screen place-items-center bg-black bg-opacity-60 opacity-0 backdrop-blur-sm transition-opacity duration-300"
    >
      <div
        class="relative m-4 w-full max-w-lg md:max-w-1/2 lg:max-w-1/3 rounded-lg bg-white font-sans text-base font-light leading-relaxed text-blue-gray-500 antialiased shadow-2xl"
      >
        <div
          class="flex items-center justify-between p-4 font-sans text-2xl antialiased font-semibold leading-snug shrink-0 text-blue-gray-900"
        >
          <h5
            class="block font-sans text-xl antialiased font-semibold leading-snug tracking-normal text-blue-gray-900"
          >
            Connect a Wallet
          </h5>
          <p
            class="block font-sans text-base antialiased font-light leading-relaxed text-gray-700"
          >
            Choose which card you want to connect
          </p>
          <button
            @click="closeDialog"
            class="relative h-8 max-h-[32px] w-8 max-w-[32px] select-none rounded-lg text-center align-middle font-sans text-xs font-medium uppercase text-blue-gray-500 transition-all hover:bg-blue-gray-500/10 active:bg-blue-gray-500/30 disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
            type="button"
          >
            <span
              class="absolute transform -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                stroke-width="2"
                class="w-5 h-5"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M6 18L18 6M6 6l12 12"
                ></path>
              </svg>
            </span>
          </button>
        </div>
        <div
          class="relative overflow-y-scroll p-4 !px-5 font-sans text-base font-light leading-relaxed text-blue-gray-500 antialiased"
        >
          <div class="mb-6">
            <p
              class="block py-3 font-sans text-base antialiased font-semibold leading-relaxed uppercase text-blue-gray-900 opacity-70"
            >
              Popular
            </p>
            <ul class="flex flex-col gap-1 mt-3 -ml-2">
              <button
                role="menuitem"
                class="mb-4 flex w-full cursor-pointer select-none items-center justify-center gap-3 rounded-md px-3 !py-4 pt-[9px] pb-2 text-start leading-tight shadow-md outline-none transition-all hover:bg-blue-gray-50 hover:bg-opacity-80 hover:text-blue-gray-900 focus:bg-blue-gray-50 focus:bg-opacity-80 focus:text-blue-gray-900 active:bg-blue-gray-50 active:bg-opacity-80 active:text-blue-gray-900"
              >
                <img
                  src="https://docs.material-tailwind.com/icons/metamask.svg"
                  alt="metamask"
                  class="w-6 h-6"
                />
                <h6
                  class="block font-sans text-base antialiased font-semibold leading-relaxed tracking-normal uppercase text-blue-gray-900"
                >
                  Connect with MetaMask
                </h6>
              </button>
              <button
                role="menuitem"
                class="mb-1 flex w-full cursor-pointer select-none items-center justify-center gap-3 rounded-md px-3 !py-4 pt-[9px] pb-2 text-start leading-tight shadow-md outline-none transition-all hover:bg-blue-gray-50 hover:bg-opacity-80 hover:text-blue-gray-900 focus:bg-blue-gray-50 focus:bg-opacity-80 focus:text-blue-gray-900 active:bg-blue-gray-50 active:bg-opacity-80 active:text-blue-gray-900"
              >
                <img
                  src="https://docs.material-tailwind.com/icons/coinbase.svg"
                  alt="coinbase"
                  class="w-6 h-6 rounded-md"
                />
                <h6
                  class="block font-sans text-base antialiased font-semibold leading-relaxed tracking-normal uppercase text-blue-gray-900"
                >
                  Connect with Coinbase
                </h6>
              </button>
            </ul>
          </div>
          <div>
            <p
              class="block py-4 font-sans text-base antialiased font-semibold leading-relaxed uppercase text-blue-gray-900 opacity-70"
            >
              Other
            </p>
            <ul class="mt-4 -ml-2.5 flex flex-col gap-1">
              <button
                role="menuitem"
                class="mb-4 flex w-full cursor-pointer select-none items-center justify-center gap-3 rounded-md px-3 !py-4 pt-[9px] pb-2 text-start leading-tight shadow-md outline-none transition-all hover:bg-blue-gray-50 hover:bg-opacity-80 hover:text-blue-gray-900 focus:bg-blue-gray-50 focus:bg-opacity-80 focus:text-blue-gray-900 active:bg-blue-gray-50 active:bg-opacity-80 active:text-blue-gray-900"
              >
                <img
                  src="https://docs.material-tailwind.com/icons/trust-wallet.svg"
                  alt="trust-wallet"
                  class="border rounded-md h-7 w-7 border-blue-gray-50"
                />
                <h6
                  class="block font-sans text-base antialiased font-semibold leading-relaxed tracking-normal uppercase text-blue-gray-900"
                >
                  Connect with Trust Wallet
                </h6>
              </button>
            </ul>
          </div>
        </div>
        <div
          class="flex flex-wrap items-center justify-between gap-2 p-4 shrink-0 text-blue-gray-500"
        >
          <p
            class="block font-sans text-sm antialiased font-normal leading-normal text-gray-700"
          >
            New to Ethereum wallets?
          </p>
          <button
            class="select-none rounded-lg border border-gray-900 py-2 px-4 text-center align-middle font-sans text-xs font-bold uppercase text-gray-900 transition-all hover:opacity-75 focus:ring focus:ring-gray-300 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
            type="button"
          >
            Learn More
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
