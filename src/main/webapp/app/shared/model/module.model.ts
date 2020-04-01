import { Moment } from 'moment';
import { IMatiere } from 'app/shared/model/matiere.model';
import { IFormateur } from 'app/shared/model/formateur.model';
import { ICursus } from 'app/shared/model/cursus.model';

export interface IModule {
  id?: number;
  nom?: string;
  dateDebut?: Moment;
  dateFin?: Moment;
  matieres?: IMatiere[];
  formateurs?: IFormateur[];
  cursuses?: ICursus[];
}

export const defaultValue: Readonly<IModule> = {};
