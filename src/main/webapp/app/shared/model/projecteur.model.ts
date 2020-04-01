import { ISalle } from 'app/shared/model/salle.model';

export interface IProjecteur {
  id?: number;
  code?: string;
  cout?: number;
  stock?: number;
  salle?: ISalle;
}

export const defaultValue: Readonly<IProjecteur> = {};
