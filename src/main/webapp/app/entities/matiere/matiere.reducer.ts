import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMatiere, defaultValue } from 'app/shared/model/matiere.model';

export const ACTION_TYPES = {
  FETCH_MATIERE_LIST: 'matiere/FETCH_MATIERE_LIST',
  FETCH_MATIERE: 'matiere/FETCH_MATIERE',
  CREATE_MATIERE: 'matiere/CREATE_MATIERE',
  UPDATE_MATIERE: 'matiere/UPDATE_MATIERE',
  DELETE_MATIERE: 'matiere/DELETE_MATIERE',
  RESET: 'matiere/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMatiere>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MatiereState = Readonly<typeof initialState>;

// Reducer

export default (state: MatiereState = initialState, action): MatiereState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MATIERE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MATIERE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MATIERE):
    case REQUEST(ACTION_TYPES.UPDATE_MATIERE):
    case REQUEST(ACTION_TYPES.DELETE_MATIERE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_MATIERE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MATIERE):
    case FAILURE(ACTION_TYPES.CREATE_MATIERE):
    case FAILURE(ACTION_TYPES.UPDATE_MATIERE):
    case FAILURE(ACTION_TYPES.DELETE_MATIERE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_MATIERE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MATIERE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MATIERE):
    case SUCCESS(ACTION_TYPES.UPDATE_MATIERE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MATIERE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/matieres';

// Actions

export const getEntities: ICrudGetAllAction<IMatiere> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MATIERE_LIST,
  payload: axios.get<IMatiere>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMatiere> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MATIERE,
    payload: axios.get<IMatiere>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMatiere> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MATIERE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMatiere> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MATIERE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMatiere> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MATIERE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
