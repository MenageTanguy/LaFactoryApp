import { Moment } from 'moment';
import { ISalle } from 'app/shared/model/salle.model';
import { IStagiaire } from 'app/shared/model/stagiaire.model';
import { IModule } from 'app/shared/model/module.model';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';

export interface ICursus {
  id?: number;
  nom?: string;
  dateDebut?: Moment;
  dateFin?: Moment;
  prerequis?: string;
  objectifs?: string;
  contenu?: string;
  salle?: ISalle;
  stagiaires?: IStagiaire[];
  modules?: IModule[];
  gestionnaire?: IGestionnaire;
}

export const defaultValue: Readonly<ICursus> = {};
