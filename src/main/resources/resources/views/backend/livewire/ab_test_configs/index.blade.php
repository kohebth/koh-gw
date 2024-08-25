<div>
    <div class="row mt-4">
        <div class="col">
            <div class="form-group row">
                <div class="col-3">
                    {{ html()->label('Feature/Test Name')->class('form-control-label') }}
                    <div>
                        <input type="text" class="form-control my-2" wire:model.defer="filter.name"/>
                    </div>
                </div>

                <div class="col-3">
                    {{html()->label('Service Name')->class('form-control-label')}}
                    <div class="pt-2">
                        <select wire:model.defer="filter.service" name="service" class="form-control">
                            <option value="">{{__("All")}}</option>
                            @foreach($services as $name)
                                <option value="{{$name}}">{{$name}}</option>
                            @endforeach
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <button type="button" title="Search" class='btn btn-info' action-search>
                    <i class="fas fa-search"></i> {{__("Search")}}
                </button>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>
            <div class="form-horizontal content-loading" style="overflow: scroll">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>Test Name</th>
                        <th>Feature</th>
                        <th>Service Name</th>
                        <th>Enabled groups</th>
                        <th>Created by</th>
                        <th>Created Time</th>
                        <th>Updated by</th>
                        <th>Updated Time</th>
                        <th width="150px">{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>

                    @if ($data->count() > 0)
                        @foreach ($data as $item)
                            <tr wire:key="_id-{{ $item->id }}">
                                <td>{{ $item->test_name }}</td>
                                <td>{{ $item->feature }}</td>
                                <td>{{ $item->service_name }}</td>
                                <td>{{ $item->enable_groups }}</td>
                                <td>{{ $item->createdBy->username ?? '' }}</td>
                                <td>{{ $item->created_at}}</td>
                                <td>{{ $item->updatedBy->username ?? ''}}</td>
                                <td>{{ $item->updated_at }}</td>
                                <td>
                                    @include('backend.includes.action_column.edit-action')
                                    @include('backend.includes.action_column.delete-action')
                                </td>
                            </tr>
                        @endforeach

                    @else
                        <tr><td colspan="12">{{__("No record")}}</td></tr>
                    @endif
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-7">
            <div class="float-left">
                {!! $data->total() !!} {{ __('labels.backend.total') }}
            </div>
        </div>
        <div class="col-5">
            <div class="float-right">
                {!! $data->links() !!}
            </div>
        </div>
    </div>
</div>

@push('after-scripts')
    <script type="text/javascript">
        $("[action-search]").on('click', function (e) {
            @this.search();
        });
    </script>
@endpush
