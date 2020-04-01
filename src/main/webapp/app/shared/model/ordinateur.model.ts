import { Moment } from 'moment';
import { IStagiaire } from 'app/shared/model/stagiaire.model';

export interface IOrdinateur {
  id?: number;
  code?: string;
  cout?: number;
  processeur?: string;
  ram?: number;
  dd?: number;
  dateAchat?: Moment;
  stock?: number;
  stagiaire?: IStagiaire;
}

export const defaultValue: Readonly<IOrdinateur> = {};
