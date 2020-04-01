import { IFormateur } from 'app/shared/model/formateur.model';
import { IModule } from 'app/shared/model/module.model';

export interface IMatiere {
  id?: number;
  nom?: string;
  duree?: number;
  formateurs?: IFormateur[];
  modules?: IModule[];
  debutantMatieres?: IFormateur[];
  intermediaireMatieres?: IFormateur[];
  avanceMatieres?: IFormateur[];
  confirmeMatieres?: IFormateur[];
}

export const defaultValue: Readonly<IMatiere> = {};
