import {AuthorityModel} from "../auth/authority.model";

export class UserModel {
  id: number;
  username: string;
  firstname: string;
  lastname: string;
  authorities: AuthorityModel[];
}

