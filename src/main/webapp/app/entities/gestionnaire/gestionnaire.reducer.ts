import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IGestionnaire, defaultValue } from 'app/shared/model/gestionnaire.model';

export const ACTION_TYPES = {
  FETCH_GESTIONNAIRE_LIST: 'gestionnaire/FETCH_GESTIONNAIRE_LIST',
  FETCH_GESTIONNAIRE: 'gestionnaire/FETCH_GESTIONNAIRE',
  CREATE_GESTIONNAIRE: 'gestionnaire/CREATE_GESTIONNAIRE',
  UPDATE_GESTIONNAIRE: 'gestionnaire/UPDATE_GESTIONNAIRE',
  DELETE_GESTIONNAIRE: 'gestionnaire/DELETE_GESTIONNAIRE',
  RESET: 'gestionnaire/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IGestionnaire>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type GestionnaireState = Readonly<typeof initialState>;

// Reducer

export default (state: GestionnaireState = initialState, action): GestionnaireState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_GESTIONNAIRE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GESTIONNAIRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GESTIONNAIRE):
    case REQUEST(ACTION_TYPES.UPDATE_GESTIONNAIRE):
    case REQUEST(ACTION_TYPES.DELETE_GESTIONNAIRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GESTIONNAIRE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GESTIONNAIRE):
    case FAILURE(ACTION_TYPES.CREATE_GESTIONNAIRE):
    case FAILURE(ACTION_TYPES.UPDATE_GESTIONNAIRE):
    case FAILURE(ACTION_TYPES.DELETE_GESTIONNAIRE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GESTIONNAIRE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_GESTIONNAIRE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GESTIONNAIRE):
    case SUCCESS(ACTION_TYPES.UPDATE_GESTIONNAIRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GESTIONNAIRE):
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

const apiUrl = 'api/gestionnaires';

// Actions

export const getEntities: ICrudGetAllAction<IGestionnaire> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_GESTIONNAIRE_LIST,
  payload: axios.get<IGestionnaire>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IGestionnaire> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GESTIONNAIRE,
    payload: axios.get<IGestionnaire>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IGestionnaire> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GESTIONNAIRE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IGestionnaire> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GESTIONNAIRE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGestionnaire> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GESTIONNAIRE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
