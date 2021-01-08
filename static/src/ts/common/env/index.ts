import EnvLocal from "./env.local";
import { IEnv } from "../../interfaces/app/IEnv";

console.log("process.env.NODE_ENV: ", process.env.NODE_ENV);
console.log("process.env.BUILD_ENV: ", process.env.BUILD_ENV);

// let Env: IEnv;
// if (process.env.BUILD_ENV === 'docker') {
//   /** docker のビルド環境の環境変数から値を取ってくる */
//   Env = {
//     envName: process.env.envName,
//     internalEndpointUrl: process.env.internalEndpointUrl,
//     externalEndpointUrl: process.env.externalEndpointUrl
//   } as IEnv
// } else {
/** docker でビルドされていない場合は、 .env.local から値を取ってくる */
// Env = EnvLocal;
// }
const Env: IEnv = EnvLocal;

console.log("Env:", Env);

export default Env;
